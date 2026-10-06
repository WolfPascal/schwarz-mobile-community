import Shared
import SwiftUI
import UIKit

struct NewsView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.NewsViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
