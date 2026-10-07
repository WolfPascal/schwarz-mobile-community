import SwiftUI
import Shared


struct SplashScreenView: UIViewControllerRepresentable {
    let onFinished: () -> Void

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.SplashScreenViewController(onFinished: onFinished)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}


struct MainView: View {
    @State private var isSplashFinished = false
    
    var body: some View {
        if !isSplashFinished {
            SplashScreenView(onFinished: {
                withAnimation {
                    isSplashFinished = true
                }
            })
            .ignoresSafeArea()
        } else {
            TabView {
                Tab {
                    NewsView()
                        .ignoresSafeArea()
                } label: {
                    Label("News", systemImage: "newspaper")
                }
                
                Tab {
                    MeetingsView()
                        .ignoresSafeArea()
                } label: {
                    Label("Meetings", systemImage: "calendar")
                }
            }
        }
    }
}
