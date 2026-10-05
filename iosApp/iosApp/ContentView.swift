import Shared
import SwiftUI
import UIKit

struct ComposeNewsView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.NewsViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ComposeMeetingsView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MeetingsViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    @State private var selectedTab: Int = 0

    var body: some View {
        if #available(iOS 26.0, *) {
            TabView(selection: $selectedTab) {
                Tab("News", systemImage: "newspaper", value: 0) {
                    ComposeNewsView()
                        .ignoresSafeArea(.all)
                }
                Tab("Meetings", systemImage: "calendar", value: 1) {
                    ComposeMeetingsView()
                        .ignoresSafeArea(.all)
                }
            }
            .tabBarMinimizeBehavior(.automatic)
            .tint(Color.accentColor)
        } else {
            TabView(selection: $selectedTab) {
                ComposeNewsView()
                    .ignoresSafeArea(.all)
                    .tabItem {
                        Label("News", systemImage: "newspaper")
                    }
                    .tag(0)

                ComposeMeetingsView()
                    .ignoresSafeArea(.all)
                    .tabItem {
                        Label("Meetings", systemImage: "calendar")
                    }
                    .tag(1)
            }
            .tint(Color.accentColor)
        }
    }
}
